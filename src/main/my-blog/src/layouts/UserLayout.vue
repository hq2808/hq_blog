<template>
  <div class="home">
    <div class="main_grapper">
      <Header />
      <div class="container">
        <router-view/>
      </div>
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

</style>