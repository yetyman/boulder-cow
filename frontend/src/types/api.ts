/* eslint-disable */
/* tslint:disable */
// @ts-nocheck
/*
 * ---------------------------------------------------------------
 * ## THIS FILE WAS GENERATED VIA SWAGGER-TYPESCRIPT-API        ##
 * ##                                                           ##
 * ## AUTHOR: acacode                                           ##
 * ## SOURCE: https://github.com/acacode/swagger-typescript-api ##
 * ---------------------------------------------------------------
 */

export type JsonNode = any;

export interface PatchRequest {
  patches?: JsonNode;
  /** @format int64 */
  clientVersion?: number;
  /** @format int64 */
  lastServerVersion?: number;
}

export interface ActionRequest {
  actionType?: string;
  locationId?: string;
  actionData?: any;
  playerId?: string;
}

export interface ActionChoice {
  choiceId?: string;
  description?: string;
  requirement?: Requirement;
  choiceData?: any;
}

export interface ActionResponse {
  valid?: boolean;
  message?: string;
  choices?: ActionChoice[];
  completed?: boolean;
}

export interface Requirement {
  consumesRequired?: boolean;
  timing?: TimingRequirement;
  requiredResources?: ResourceEntry;
}

export interface ResourceEntry {
  resources?: (
    | "rye"
    | "flax"
    | "barley"
    | "hops"
    | "meat"
    | "milk"
    | "wool"
    | "clay"
    | "leather"
    | "sheep"
    | "vp"
    | "jewelry"
    | "boulder"
    | "worker"
    | "cock"
    | "tool"
    | "fieldAnyLvl"
    | "fieldLvl2"
    | "fieldLvl3"
    | "fieldLvl4"
    | "fieldLvl5"
    | "fieldLvl5WithCrop"
    | "fieldWithCrop"
    | "fieldWithoutCrop"
    | "gatewayCard"
    | "farmyardCard"
    | "bonusCard"
    | "pointCard"
    | "workerSupply"
    | "workersRemaining"
    | "differentCrops"
    | "anyGoods"
    | "anyCrops"
    | "actionSpace"
    | "actionSpaceFullyOccupied"
    | "sheepMovementDifferent"
    | "farmyardCardsPlayed"
    | "sheepOnSameFarmyard"
    | "sheepOnFarmyard"
    | "nextTurnSheepCard"
    | "anyCard"
    | "hand"
    | "emptyFarmyardCards"
    | "cardsPlayed"
    | "nursery"
    | "roundNumber"
    | "quadrant"
    | "craftBuilding"
    | "row2"
    | "row3"
    | "fullTopRow"
    | "level"
    | "removeWorkersAction"
    | "harvestAction"
    | "sheepMovement"
    | "sowAnyAction"
    | "sowSpecificAction"
    | "fieldUpgrade"
    | "justBeforeLastAction"
    | "sheepRemoval"
    | "workersRemoved"
    | "craftBuildingAdvanced"
    | "butcheryActionUsed"
    | "toolsObtained"
    | "farmingActionUsed"
    | "workersExchangedForTools"
    | "cardPlayedAfterGotten"
    | "justHarvested"
    | "jewelrySpent"
    | "clearActionSpaces"
    | "useActionSpace"
    | "sowDoubleCrop"
    | "moveSheepBackward"
    | "doubleHarvest"
    | "harvest"
    | "harvestTotal"
    | "harvestedSameTypeCrops"
    | "exchange"
    | "workerPlacementRequirement"
    | "deckTile"
    | "resourceTile"
    | "activeCards"
    | "homeBoard"
    | "treasureChest"
    | "turnTracker"
    | "resourceTracker"
  )[];
  modifiers?: (
    | "CHOOSE"
    | "DISTINCT"
    | "SAME"
    | "SUBTRACT"
    | "REV_SUBTRACT"
    | "PER"
    | "STAGED"
    | "EXACTLY"
    | "MORE_THAN"
    | "TOTAL"
    | "DIFFERENT"
    | "EACH"
    | "MIN_OF"
    | "REACTION"
    | "UP_TO"
    | "MAX_OF"
    | "CONSUME"
    | "ON"
    | "UPGRADE"
    | "TO"
    | "REMOVED"
    | "LEVEL"
    | "WITH"
    | "NOT"
  )[];
  values?: number[];
  referenceUnits?: (
    | "rye"
    | "flax"
    | "barley"
    | "hops"
    | "meat"
    | "milk"
    | "wool"
    | "clay"
    | "leather"
    | "sheep"
    | "vp"
    | "jewelry"
    | "boulder"
    | "worker"
    | "cock"
    | "tool"
    | "fieldAnyLvl"
    | "fieldLvl2"
    | "fieldLvl3"
    | "fieldLvl4"
    | "fieldLvl5"
    | "fieldLvl5WithCrop"
    | "fieldWithCrop"
    | "fieldWithoutCrop"
    | "gatewayCard"
    | "farmyardCard"
    | "bonusCard"
    | "pointCard"
    | "workerSupply"
    | "workersRemaining"
    | "differentCrops"
    | "anyGoods"
    | "anyCrops"
    | "actionSpace"
    | "actionSpaceFullyOccupied"
    | "sheepMovementDifferent"
    | "farmyardCardsPlayed"
    | "sheepOnSameFarmyard"
    | "sheepOnFarmyard"
    | "nextTurnSheepCard"
    | "anyCard"
    | "hand"
    | "emptyFarmyardCards"
    | "cardsPlayed"
    | "nursery"
    | "roundNumber"
    | "quadrant"
    | "craftBuilding"
    | "row2"
    | "row3"
    | "fullTopRow"
    | "level"
    | "removeWorkersAction"
    | "harvestAction"
    | "sheepMovement"
    | "sowAnyAction"
    | "sowSpecificAction"
    | "fieldUpgrade"
    | "justBeforeLastAction"
    | "sheepRemoval"
    | "workersRemoved"
    | "craftBuildingAdvanced"
    | "butcheryActionUsed"
    | "toolsObtained"
    | "farmingActionUsed"
    | "workersExchangedForTools"
    | "cardPlayedAfterGotten"
    | "justHarvested"
    | "jewelrySpent"
    | "clearActionSpaces"
    | "useActionSpace"
    | "sowDoubleCrop"
    | "moveSheepBackward"
    | "doubleHarvest"
    | "harvest"
    | "harvestTotal"
    | "harvestedSameTypeCrops"
    | "exchange"
    | "workerPlacementRequirement"
    | "deckTile"
    | "resourceTile"
    | "activeCards"
    | "homeBoard"
    | "treasureChest"
    | "turnTracker"
    | "resourceTracker"
  )[];
  referenceValues?: number[];
  subEntries?: any[];
}

export interface TimingRequirement {
  requiredPhase?:
    | "removeWorkers"
    | "newWorkers"
    | "income"
    | "actions"
    | "newCard"
    | "fallowFields"
    | "harvest"
    | "milking"
    | "buildings"
    | "boulders";
  invertPhaseRequirement?: boolean;
  reactionTo?:
    | "rye"
    | "flax"
    | "barley"
    | "hops"
    | "meat"
    | "milk"
    | "wool"
    | "clay"
    | "leather"
    | "sheep"
    | "vp"
    | "jewelry"
    | "boulder"
    | "worker"
    | "cock"
    | "tool"
    | "fieldAnyLvl"
    | "fieldLvl2"
    | "fieldLvl3"
    | "fieldLvl4"
    | "fieldLvl5"
    | "fieldLvl5WithCrop"
    | "fieldWithCrop"
    | "fieldWithoutCrop"
    | "gatewayCard"
    | "farmyardCard"
    | "bonusCard"
    | "pointCard"
    | "workerSupply"
    | "workersRemaining"
    | "differentCrops"
    | "anyGoods"
    | "anyCrops"
    | "actionSpace"
    | "actionSpaceFullyOccupied"
    | "sheepMovementDifferent"
    | "farmyardCardsPlayed"
    | "sheepOnSameFarmyard"
    | "sheepOnFarmyard"
    | "nextTurnSheepCard"
    | "anyCard"
    | "hand"
    | "emptyFarmyardCards"
    | "cardsPlayed"
    | "nursery"
    | "roundNumber"
    | "quadrant"
    | "craftBuilding"
    | "row2"
    | "row3"
    | "fullTopRow"
    | "level"
    | "removeWorkersAction"
    | "harvestAction"
    | "sheepMovement"
    | "sowAnyAction"
    | "sowSpecificAction"
    | "fieldUpgrade"
    | "justBeforeLastAction"
    | "sheepRemoval"
    | "workersRemoved"
    | "craftBuildingAdvanced"
    | "butcheryActionUsed"
    | "toolsObtained"
    | "farmingActionUsed"
    | "workersExchangedForTools"
    | "cardPlayedAfterGotten"
    | "justHarvested"
    | "jewelrySpent"
    | "clearActionSpaces"
    | "useActionSpace"
    | "sowDoubleCrop"
    | "moveSheepBackward"
    | "doubleHarvest"
    | "harvest"
    | "harvestTotal"
    | "harvestedSameTypeCrops"
    | "exchange"
    | "workerPlacementRequirement"
    | "deckTile"
    | "resourceTile"
    | "activeCards"
    | "homeBoard"
    | "treasureChest"
    | "turnTracker"
    | "resourceTracker";
  repeats?: boolean;
  nonPassive?: boolean;
}

export interface Boulder {
  /** @format int32 */
  value?: number;
}

export interface Building {
  /** @format int32 */
  value?: number;
  movement?: ReqAndEffect;
}

export interface BuildingTracker {
  /** @format int32 */
  maxValue?: number;
  building?: Building;
  boulder1?: Boulder;
  boulder2?: Boulder;
}

export interface Card {
  /** @format int32 */
  appendixIndex?: number;
  title?: string;
  reqAndEffect?: ReqAndEffect;
  cardImage?: ImageRef;
  description?: string;
  symbols?: SymbolicDisplay;
  cardType?:
    | "rye"
    | "flax"
    | "barley"
    | "hops"
    | "meat"
    | "milk"
    | "wool"
    | "clay"
    | "leather"
    | "sheep"
    | "vp"
    | "jewelry"
    | "boulder"
    | "worker"
    | "cock"
    | "tool"
    | "fieldAnyLvl"
    | "fieldLvl2"
    | "fieldLvl3"
    | "fieldLvl4"
    | "fieldLvl5"
    | "fieldLvl5WithCrop"
    | "fieldWithCrop"
    | "fieldWithoutCrop"
    | "gatewayCard"
    | "farmyardCard"
    | "bonusCard"
    | "pointCard"
    | "workerSupply"
    | "workersRemaining"
    | "differentCrops"
    | "anyGoods"
    | "anyCrops"
    | "actionSpace"
    | "actionSpaceFullyOccupied"
    | "sheepMovementDifferent"
    | "farmyardCardsPlayed"
    | "sheepOnSameFarmyard"
    | "sheepOnFarmyard"
    | "nextTurnSheepCard"
    | "anyCard"
    | "hand"
    | "emptyFarmyardCards"
    | "cardsPlayed"
    | "nursery"
    | "roundNumber"
    | "quadrant"
    | "craftBuilding"
    | "row2"
    | "row3"
    | "fullTopRow"
    | "level"
    | "removeWorkersAction"
    | "harvestAction"
    | "sheepMovement"
    | "sowAnyAction"
    | "sowSpecificAction"
    | "fieldUpgrade"
    | "justBeforeLastAction"
    | "sheepRemoval"
    | "workersRemoved"
    | "craftBuildingAdvanced"
    | "butcheryActionUsed"
    | "toolsObtained"
    | "farmingActionUsed"
    | "workersExchangedForTools"
    | "cardPlayedAfterGotten"
    | "justHarvested"
    | "jewelrySpent"
    | "clearActionSpaces"
    | "useActionSpace"
    | "sowDoubleCrop"
    | "moveSheepBackward"
    | "doubleHarvest"
    | "harvest"
    | "harvestTotal"
    | "harvestedSameTypeCrops"
    | "exchange"
    | "workerPlacementRequirement"
    | "deckTile"
    | "resourceTile"
    | "activeCards"
    | "homeBoard"
    | "treasureChest"
    | "turnTracker"
    | "resourceTracker";
  resources?: ResourceEntry;
}

export type DeckImage = any;

export interface DeckTile {
  img?: DeckImage;
  deck?: Card[];
  title?: TitleText;
  row2?: WorkerPlacementLocation;
  row1?: WorkerPlacementLocation;
  reqAndEffect?: ReqAndEffect;
}

export interface Effect {
  givesResources?: ResourceEntry;
  timing?: TimingRequirement;
}

export interface FarmLand {
  /** @format int32 */
  value?: number;
  /** @format int32 */
  maxValue?: number;
  resource?:
    | "rye"
    | "flax"
    | "barley"
    | "hops"
    | "meat"
    | "milk"
    | "wool"
    | "clay"
    | "leather"
    | "sheep"
    | "vp"
    | "jewelry"
    | "boulder"
    | "worker"
    | "cock"
    | "tool"
    | "fieldAnyLvl"
    | "fieldLvl2"
    | "fieldLvl3"
    | "fieldLvl4"
    | "fieldLvl5"
    | "fieldLvl5WithCrop"
    | "fieldWithCrop"
    | "fieldWithoutCrop"
    | "gatewayCard"
    | "farmyardCard"
    | "bonusCard"
    | "pointCard"
    | "workerSupply"
    | "workersRemaining"
    | "differentCrops"
    | "anyGoods"
    | "anyCrops"
    | "actionSpace"
    | "actionSpaceFullyOccupied"
    | "sheepMovementDifferent"
    | "farmyardCardsPlayed"
    | "sheepOnSameFarmyard"
    | "sheepOnFarmyard"
    | "nextTurnSheepCard"
    | "anyCard"
    | "hand"
    | "emptyFarmyardCards"
    | "cardsPlayed"
    | "nursery"
    | "roundNumber"
    | "quadrant"
    | "craftBuilding"
    | "row2"
    | "row3"
    | "fullTopRow"
    | "level"
    | "removeWorkersAction"
    | "harvestAction"
    | "sheepMovement"
    | "sowAnyAction"
    | "sowSpecificAction"
    | "fieldUpgrade"
    | "justBeforeLastAction"
    | "sheepRemoval"
    | "workersRemoved"
    | "craftBuildingAdvanced"
    | "butcheryActionUsed"
    | "toolsObtained"
    | "farmingActionUsed"
    | "workersExchangedForTools"
    | "cardPlayedAfterGotten"
    | "justHarvested"
    | "jewelrySpent"
    | "clearActionSpaces"
    | "useActionSpace"
    | "sowDoubleCrop"
    | "moveSheepBackward"
    | "doubleHarvest"
    | "harvest"
    | "harvestTotal"
    | "harvestedSameTypeCrops"
    | "exchange"
    | "workerPlacementRequirement"
    | "deckTile"
    | "resourceTile"
    | "activeCards"
    | "homeBoard"
    | "treasureChest"
    | "turnTracker"
    | "resourceTracker";
}

export interface GameState {
  table?: Table;
  /** @format int64 */
  version?: number;
}

export type HomeBGImage = any;

export interface HomeBoard {
  homeBGImage?: HomeBGImage;
  homeImage?: HomeImage;
  buildingRows?: BuildingTracker[];
}

export type HomeImage = any;

export interface ImageRef {
  /** @format uri */
  uri?: string;
  description?: string;
  /** @format double */
  aspectRatio?: number;
}

export interface Player {
  name?: string;
}

export interface PlayerArea {
  player?: Player;
  activeCards?: Card[];
  homeBoard?: HomeBoard;
  treasureChest?: TreasureChest;
  turnTracker?: TurnTracker;
  resourceTracker?: ResourceTracker;
  hand?: Card[];
}

export interface ReqAndEffect {
  req?: Requirement;
  effect?: Effect;
}

export interface ResourceTile {
  image?: ImageRef;
  title?: TitleText;
  row3?: WorkerPlacementLocation;
  row2?: WorkerPlacementLocation;
  row1?: WorkerPlacementLocation;
  symbolicDisplay?: SymbolicDisplay;
}

export interface ResourceTracker {
  farms?: FarmLand[];
  /** @format int32 */
  maxValue?: number;
  resources?: ResourceEntry;
}

export interface SharedBoard {
  resourceTiles?: ResourceTile[][];
  deckTiles?: DeckTile[][];
}

export interface SymbolicDisplay {
  subSymbols?: any[];
  image?: ImageRef;
  description?: string;
  arrangement?:
    | "HORIZONTAL"
    | "VERTICAL"
    | "CIRCULAR"
    | "OVERLAP"
    | "OFFSET_OVERLAP";
}

export interface Table {
  sharedBoard?: SharedBoard;
  playerAreas?: PlayerArea[];
  /** @format int32 */
  currentPlayerIndex?: number;
}

export interface TitleText {
  title?: string;
}

export interface TreasureChest {
  rings?: ResourceEntry;
}

export interface TurnTracker {
  /** @format int32 */
  rounds?: number;
  cards?: Card[];
  sheeps?: ResourceEntry[];
}

export interface WorkerPlacementLocation {
  /** @format int32 */
  playerId?: number;
  /** @format int32 */
  requiredWorkerCount?: number;
}

export type QueryParamsType = Record<string | number, any>;
export type ResponseFormat = keyof Omit<Body, "body" | "bodyUsed">;

export interface FullRequestParams extends Omit<RequestInit, "body"> {
  /** set parameter to `true` for call `securityWorker` for this request */
  secure?: boolean;
  /** request path */
  path: string;
  /** content type of request body */
  type?: ContentType;
  /** query params */
  query?: QueryParamsType;
  /** format of response (i.e. response.json() -> format: "json") */
  format?: ResponseFormat;
  /** request body */
  body?: unknown;
  /** base url */
  baseUrl?: string;
  /** request cancellation token */
  cancelToken?: CancelToken;
}

export type RequestParams = Omit<
  FullRequestParams,
  "body" | "method" | "query" | "path"
>;

export interface ApiConfig<SecurityDataType = unknown> {
  baseUrl?: string;
  baseApiParams?: Omit<RequestParams, "baseUrl" | "cancelToken" | "signal">;
  securityWorker?: (
    securityData: SecurityDataType | null,
  ) => Promise<RequestParams | void> | RequestParams | void;
  customFetch?: typeof fetch;
}

export interface HttpResponse<D extends unknown, E extends unknown = unknown>
  extends Response {
  data: D;
  error: E;
}

type CancelToken = Symbol | string | number;

export enum ContentType {
  Json = "application/json",
  JsonApi = "application/vnd.api+json",
  FormData = "multipart/form-data",
  UrlEncoded = "application/x-www-form-urlencoded",
  Text = "text/plain",
}

export class HttpClient<SecurityDataType = unknown> {
  public baseUrl: string = "http://localhost:8080";
  private securityData: SecurityDataType | null = null;
  private securityWorker?: ApiConfig<SecurityDataType>["securityWorker"];
  private abortControllers = new Map<CancelToken, AbortController>();
  private customFetch = (...fetchParams: Parameters<typeof fetch>) =>
    fetch(...fetchParams);

  private baseApiParams: RequestParams = {
    credentials: "same-origin",
    headers: {},
    redirect: "follow",
    referrerPolicy: "no-referrer",
  };

  constructor(apiConfig: ApiConfig<SecurityDataType> = {}) {
    Object.assign(this, apiConfig);
  }

  public setSecurityData = (data: SecurityDataType | null) => {
    this.securityData = data;
  };

  protected encodeQueryParam(key: string, value: any) {
    const encodedKey = encodeURIComponent(key);
    return `${encodedKey}=${encodeURIComponent(typeof value === "number" ? value : `${value}`)}`;
  }

  protected addQueryParam(query: QueryParamsType, key: string) {
    return this.encodeQueryParam(key, query[key]);
  }

  protected addArrayQueryParam(query: QueryParamsType, key: string) {
    const value = query[key];
    return value.map((v: any) => this.encodeQueryParam(key, v)).join("&");
  }

  protected toQueryString(rawQuery?: QueryParamsType): string {
    const query = rawQuery || {};
    const keys = Object.keys(query).filter(
      (key) => "undefined" !== typeof query[key],
    );
    return keys
      .map((key) =>
        Array.isArray(query[key])
          ? this.addArrayQueryParam(query, key)
          : this.addQueryParam(query, key),
      )
      .join("&");
  }

  protected addQueryParams(rawQuery?: QueryParamsType): string {
    const queryString = this.toQueryString(rawQuery);
    return queryString ? `?${queryString}` : "";
  }

  private contentFormatters: Record<ContentType, (input: any) => any> = {
    [ContentType.Json]: (input: any) =>
      input !== null && (typeof input === "object" || typeof input === "string")
        ? JSON.stringify(input)
        : input,
    [ContentType.JsonApi]: (input: any) =>
      input !== null && (typeof input === "object" || typeof input === "string")
        ? JSON.stringify(input)
        : input,
    [ContentType.Text]: (input: any) =>
      input !== null && typeof input !== "string"
        ? JSON.stringify(input)
        : input,
    [ContentType.FormData]: (input: any) => {
      if (input instanceof FormData) {
        return input;
      }

      return Object.keys(input || {}).reduce((formData, key) => {
        const property = input[key];
        formData.append(
          key,
          property instanceof Blob
            ? property
            : typeof property === "object" && property !== null
              ? JSON.stringify(property)
              : `${property}`,
        );
        return formData;
      }, new FormData());
    },
    [ContentType.UrlEncoded]: (input: any) => this.toQueryString(input),
  };

  protected mergeRequestParams(
    params1: RequestParams,
    params2?: RequestParams,
  ): RequestParams {
    return {
      ...this.baseApiParams,
      ...params1,
      ...(params2 || {}),
      headers: {
        ...(this.baseApiParams.headers || {}),
        ...(params1.headers || {}),
        ...((params2 && params2.headers) || {}),
      },
    };
  }

  protected createAbortSignal = (
    cancelToken: CancelToken,
  ): AbortSignal | undefined => {
    if (this.abortControllers.has(cancelToken)) {
      const abortController = this.abortControllers.get(cancelToken);
      if (abortController) {
        return abortController.signal;
      }
      return void 0;
    }

    const abortController = new AbortController();
    this.abortControllers.set(cancelToken, abortController);
    return abortController.signal;
  };

  public abortRequest = (cancelToken: CancelToken) => {
    const abortController = this.abortControllers.get(cancelToken);

    if (abortController) {
      abortController.abort();
      this.abortControllers.delete(cancelToken);
    }
  };

  public request = async <T = any, E = any>({
    body,
    secure,
    path,
    type,
    query,
    format,
    baseUrl,
    cancelToken,
    ...params
  }: FullRequestParams): Promise<HttpResponse<T, E>> => {
    const secureParams =
      ((typeof secure === "boolean" ? secure : this.baseApiParams.secure) &&
        this.securityWorker &&
        (await this.securityWorker(this.securityData))) ||
      {};
    const requestParams = this.mergeRequestParams(params, secureParams);
    const queryString = query && this.toQueryString(query);
    const payloadFormatter = this.contentFormatters[type || ContentType.Json];
    const responseFormat = format || requestParams.format;

    return this.customFetch(
      `${baseUrl || this.baseUrl || ""}${path}${queryString ? `?${queryString}` : ""}`,
      {
        ...requestParams,
        headers: {
          ...(requestParams.headers || {}),
          ...(type && type !== ContentType.FormData
            ? { "Content-Type": type }
            : {}),
        },
        signal:
          (cancelToken
            ? this.createAbortSignal(cancelToken)
            : requestParams.signal) || null,
        body:
          typeof body === "undefined" || body === null
            ? null
            : payloadFormatter(body),
      },
    ).then(async (response) => {
      const r = response.clone() as HttpResponse<T, E>;
      r.data = null as unknown as T;
      r.error = null as unknown as E;

      const data = !responseFormat
        ? r
        : await response[responseFormat]()
            .then((data) => {
              if (r.ok) {
                r.data = data;
              } else {
                r.error = data;
              }
              return r;
            })
            .catch((e) => {
              r.error = e;
              return r;
            });

      if (cancelToken) {
        this.abortControllers.delete(cancelToken);
      }

      if (!response.ok) throw data;
      return data;
    });
  };
}

/**
 * @title OpenAPI definition
 * @version v0
 * @baseUrl http://localhost:8080
 */
export class Api<
  SecurityDataType extends unknown,
> extends HttpClient<SecurityDataType> {
  api = {
    /**
     * No description
     *
     * @tags game-controller
     * @name Undo
     * @request POST:/api/game/undo
     */
    undo: (params: RequestParams = {}) =>
      this.request<object, any>({
        path: `/api/game/undo`,
        method: "POST",
        ...params,
      }),

    /**
     * No description
     *
     * @tags game-controller
     * @name ApplyPatch
     * @request POST:/api/game/patch
     */
    applyPatch: (data: PatchRequest, params: RequestParams = {}) =>
      this.request<object, any>({
        path: `/api/game/patch`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags game-controller
     * @name PerformAction
     * @request POST:/api/game/action
     */
    performAction: (data: ActionRequest, params: RequestParams = {}) =>
      this.request<ActionResponse, any>({
        path: `/api/game/action`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags test-controller
     * @name Hello
     * @request GET:/api/test/hello
     */
    hello: (params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/test/hello`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags game-controller
     * @name GetGameState
     * @request GET:/api/game/state
     */
    getGameState: (params: RequestParams = {}) =>
      this.request<GameState, any>({
        path: `/api/game/state`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags game-controller
     * @name GetStateDelta
     * @request GET:/api/game/delta
     */
    getStateDelta: (
      query?: {
        /** @format int64 */
        arg0?: number;
      },
      params: RequestParams = {},
    ) =>
      this.request<object, any>({
        path: `/api/game/delta`,
        method: "GET",
        query: query,
        ...params,
      }),
  };
}
