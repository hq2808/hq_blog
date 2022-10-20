<template>
  <div>
    <ListBlogCard :data="data" />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import { postService } from '@/services';
import ListBlogCard from '@/components/shared/ListBlogCard.vue';
import { DataPageRequest } from '@/models'

@Component({
  components: {
    ListBlogCard,
  },
})
export default class PostListPage extends Vue {
  private data = [];
  private request: DataPageRequest = new DataPageRequest();
  async created() {
    await postService.get(this.request).then(res => {
      this.data = res.data.data.content;
    })
  }
}
</script>
<style scoped>
.column {
  float: left;
  width: 50%;
  padding: 10px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}
</style>