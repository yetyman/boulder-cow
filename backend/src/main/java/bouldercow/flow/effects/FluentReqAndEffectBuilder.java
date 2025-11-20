package bouldercow.flow.effects;

import bouldercow.flow.Phase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static bouldercow.flow.effects.EffectModifier.EACH;

public class FluentReqAndEffectBuilder {
    private Requirement requirement;
    private Effect effect;

    // Entry points
    public static ReqAndEffectBuilder require() {
        return new ReqAndEffectBuilder(new FluentReqAndEffectBuilder());
    }

    public static ReqAndEffectBuilder consume() {
        ReqAndEffectBuilder builder = require();
        builder.consumes = true;
        return builder;
    }

    // Requirement Builder
    public static class ReqAndEffectBuilder {
        private final Object parent;
        private final List<ResourceEntry> entries = new ArrayList<>();
        private final List<EffectModifier> multiEntryModifiers = new ArrayList<>();

        private final List<ResourceUnits> currentUnits = new ArrayList<>();
        private final List<Double> currentAmounts = new ArrayList<>();
        private final List<EffectModifier> currentModifiers = new ArrayList<>();
        private final List<ResourceUnits> currentReferences = new ArrayList<>();
        
        private TimingRequirement timing;
        boolean consumes = false;

        ReqAndEffectBuilder(Object parent) {
            this.parent = parent;
        }



        public ReqAndEffectBuilder units(ResourceUnits... units) {
            this.currentUnits.addAll(List.of(units));
            return this;
        }

        public ReqAndEffectBuilder u(ResourceUnits... units) {
            return units(units);
        }

        public ReqAndEffectBuilder amounts(double... amounts) {
            for (double amount : amounts) {
                this.currentAmounts.add(amount);
            }
            return this;
        }

        public ReqAndEffectBuilder a(double... amounts) {
            return amounts(amounts);
        }

        public ReqAndEffectBuilder amount(double amount) {
            this.currentAmounts.add(amount);
            return this;
        }

        public ReqAndEffectBuilder mod(EffectModifier modifier, double value1, double... values) {
            //value1 disambiguates the method calls
            this.currentModifiers.add(modifier);
            this.currentAmounts.add(value1);
            for (double value : values) {
                this.currentAmounts.add(value);
            }
            return this;
        }

        public ReqAndEffectBuilder m(EffectModifier modifier, double value1, double... values) {
            return mod(modifier, value1, values);
        }


        public ReqAndEffectBuilder mod(EffectModifier... modifiers) {
            this.currentModifiers.addAll(Arrays.asList(modifiers));
            return this;
        }

        public ReqAndEffectBuilder m(EffectModifier... modifiers) {
            return mod(modifiers);
        }

        public ReqAndEffectBuilder mod(EffectModifier modifier, ResourceUnits reference) {
            this.currentModifiers.add(modifier);
            this.currentReferences.add(reference);
            return this;
        }

        public ReqAndEffectBuilder m(EffectModifier modifier, ResourceUnits reference) {
            return mod(modifier, reference);
        }

        public ReqAndEffectBuilder timing(TimingRequirement timing) {
            this.timing = timing;
            return this;
        }

        public ReqAndEffectBuilder timing(Phase phase) {
            this.timing = TimingRequirement.timing(phase);
            return this;
        }
        public ReqAndEffectBuilder timing(Phase phase, boolean invertPhase) {
            this.timing = TimingRequirement.timing(phase, invertPhase);
            return this;
        }
        public ReqAndEffectBuilder timing(ResourceUnits action) {
            this.timing = TimingRequirement.timing(action);
            return this;
        }
        public ReqAndEffectBuilder timing(Phase phase, ResourceUnits action) {
            this.timing = TimingRequirement.timing(phase, action);
            return this;
        }
        public ReqAndEffectBuilder t(Phase phase) {
            return timing(phase);
        }
        public ReqAndEffectBuilder t(Phase phase, boolean invertPhase) {
            return timing(phase, invertPhase);
        }
        public ReqAndEffectBuilder t(ResourceUnits action) {
            return timing(action);
        }
        public ReqAndEffectBuilder t(Phase phase, ResourceUnits action) {
            return timing(phase, action);
        }

        public ReqAndEffectBuilder repeats(Phase phase) {
            this.timing = TimingRequirement.timing(phase);
            this.timing.repeats = true;
            return this;
        }
        public ReqAndEffectBuilder r(Phase phase) {
            return this.repeats(phase);
        }

        private ResourceEntry buildCurrentEntry() {
            return ResourceEntry.create(currentUnits, currentAmounts, currentModifiers, currentReferences);
        }
        public ReqAndEffectBuilder and() {
            return and(EACH);
        }
        public ReqAndEffectBuilder and(EffectModifier modifier) {
            // Finalize current entry and start new one
            entries.add(buildCurrentEntry());
            multiEntryModifiers.add(modifier);
            clearCurrent();
            return this;
        }
        public ReqAndEffectBuilder give() {
            // Build the requirement
            entries.add(buildCurrentEntry());

            // Create requirement with multiple entries if needed
            if (parent instanceof FluentReqAndEffectBuilder fluentParent) {
                if (entries.size() == 1) {
                    fluentParent.requirement = Requirement.of(entries.getFirst(), consumes);
                } else {
                    fluentParent.requirement = Requirement.of(ResourceEntry.withSubEntries(multiEntryModifiers, entries.toArray(new ResourceEntry[0])), consumes);
                }
                if (timing != null)
                    fluentParent.requirement.timing = timing;
                
                return new ReqAndEffectBuilder(fluentParent);//moving on to the effect portion of things
            }
            else {
                return parent().give();
            }
        }

        public ReqAndEffect complete() {
            // Add final entry
            entries.add(buildCurrentEntry());

            // Create effect with multiple entries if needed
            if (parent instanceof FluentReqAndEffectBuilder fluentParent) {
                if (entries.size() == 1) {
                    fluentParent.effect = Effect.of(entries.getFirst());
                } else {
                    fluentParent.effect = Effect.of(ResourceEntry.withSubEntries(multiEntryModifiers, entries.toArray(new ResourceEntry[0])));
                }
                if (timing != null)
                    fluentParent.effect.timing = timing;

                return new ReqAndEffect(fluentParent.requirement, fluentParent.effect);
            }
            else {
                return parent().complete();
            }
        }


        public ReqAndEffectBuilder sub() {
            return new ReqAndEffectBuilder(this);
        }

        public ReqAndEffectBuilder parent() {
            if (parent instanceof ReqAndEffectBuilder parentBuilder) {
                parentBuilder.entries.add(buildCurrentEntry());
                return parentBuilder;
            }
            else
                throw new RuntimeException("Calling parent when not in a sub effect builder");
        }

        private void clearCurrent() {
            currentUnits.clear();
            currentAmounts.clear();
            currentModifiers.clear();
            currentReferences.clear();;
        }
    }

}