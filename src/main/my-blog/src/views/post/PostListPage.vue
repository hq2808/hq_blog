<template>
  <div>
    <div class="row" v-if="data.length > 0">
      <div class="column " v-for="(item, index) in data" :key="item.id">
        <BlogCard :index="index" :item="item" />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import { postService } from '@/services';
import ItemCard from '@/components/shared/ItemCard.vue';
import BlogCard from '@/components/shared/BlogCard.vue';
import { DataTableRequest } from '@/models/shared/DataTableRequest'

@Component({
  components: {
    ItemCard,
    BlogCard
  },
})
export default class PostListPage extends Vue {
  private data = [];
  private request: DataTableRequest = new DataTableRequest();
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