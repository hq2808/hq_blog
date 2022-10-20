<template>
  <div>
    <div class="row" v-if="data.length > 0">
      <div class="column " v-for="(item, index) in data" :key="item.id">
        <BlogCard :index="index" :item="item" />
      </div>
    </div>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
    ></b-pagination>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator';
import { postService } from '@/services';
import BlogCard from '@/components/shared/BlogCard.vue';
import { DataPageSetting } from '@/models'

@Component({
  components: {
    BlogCard
  },
})
export default class ListBlogCard extends Vue {
  @Prop() public setting!: DataPageSetting;
  @Prop() public currentPage!: number;
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