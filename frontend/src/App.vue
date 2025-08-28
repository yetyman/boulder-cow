<template>
  <div id="app">
    <GameTable />
  </div>
</template>

<script>
import GameTable from './components/GameTable.vue'

export default {
  name: 'App',
  components: {
    GameTable
  }
}
</script>

<style>
#app {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 20px;
}
</style>