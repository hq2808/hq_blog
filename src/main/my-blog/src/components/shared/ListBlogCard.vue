<template>
  <div ref="page" v-if="setting.items.length > 0">
    <b-row v-for="(item, index) in setting.items" :key="item.id">
      <b-col >
        <BlogCard :index="index" :item="item" @goToDetail="goToDetail(item.id)" @readMore="goReadMore(item.id)"/>
      </b-col>
    </b-row>
    <div class="d-flex flex-end mt-3">
      <!-- <b-form-select
        :options="setting.pageOptions"
        :value="setting.perPage"
        @change="changePageSize"
        class="page-selector"
      /> -->
      <b-pagination
        v-model="currentPage"
        :total-rows="setting.totalPosts"
        :per-page="setting.perPage"
        aria-controls="my-table"
        @change="changePageNumber">
      </b-pagination>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator';
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

  private changePageSize(size: number) {
    this.$emit('changePageSize', size);
  }

  private changePageNumber(page: number) {
    this.$emit('changePageNumber', page);
  }

  private goToDetail(id: any) {
    this.$emit('goToDetail', id);
  }
  private goReadMore(id: any) {
    this.$emit('goReadMore', id);
  }
  private refreshTable() {
    (this.$refs.page as any).refresh();
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
  @media (max-width: 640px) {
    .column {
      float: left;
      width: 100%;
      padding: 10px;
    }
  }
  .page-selector {
    width: auto;
    /* margin-right: 20px; */
    font-weight: 400;
    border: 1px solid #e4e7ea;
    background-color: #fff;
    width: 40px;
    height: 38px;
    border-radius: 5px;
    text-align: center;
    margin-right: 20px;
  }
</style>