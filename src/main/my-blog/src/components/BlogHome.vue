<template>
 <div>
  Blog Home
  {{getString()}}
 </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';

@Component
export default class BlogHome extends Vue {
  private prefecture?:string = 'áđá';
  created() {
    debugger;
   console.log("Hello");
  }

  private getString() {
    return this.prefecture;
  }
}
</script>
