<template>
  <div id="app">
    <nav-header></nav-header>
    <router-view id="main" />
    <main-footer></main-footer>
  </div>
</template>

<script>
import NavHeader from '@/components/navibar';
import MainFooter from '@/components/footer.vue';
export default {
  name: 'app',
  components: {
    NavHeader,
    MainFooter,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#main {
  margin-top: 140px;
}
</style>
