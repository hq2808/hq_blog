<template>
  <div>
    <Category product="headphones" />
  </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';
import Category from './menu/Category.vue';
@Component({
  components: {
    Category,
  },
})
export default class Menu extends Vue {

}
</script>
