<template>
  <div>
    <div v-html="data.content" class="text-break"></div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import { postService } from '@/services';

@Component
export default class ReadPost extends Vue {
  private id?: string = '';
  private data = {};
  private created() {
    if(this.$router.currentRoute.params.slug) {
      this.id = this.$router.currentRoute.params.slug;
      postService.get(this.id).then(res => {
        if(res.data.data) {
          this.data = res.data.data;
        }
     })
    }
  }
}
</script>
