<template>
    <div class="home">
    <header class="main-navbar">
      <Header />
    </header>
    <div class="container mt-3">
      <router-view/>
    </div>
    <Footer />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
@Component({
  components: {
    Header,
    Footer
  },
})
export default class UserLayout extends Vue {}
</script>
<style scoped>
  .main-navbar {
    position: -webkit-sticky;
    position: sticky;
    width: 100%;
    top: 0;
    z-index: 1030;
    background-color: #fff;
    box-shadow: 0 4px 4px 0 rgb(0 0 0 / 10%);
    height: 88px;
  }
</style>