<template>
  <div>
    <b-row v-if="isAdmin" class="justify-content-center">
      <b-col cols="auto" >
        <b-button @click="addNewPost" variant="success">+</b-button>
      </b-col>
    </b-row>
    <ListBlogCard
      ref="listBlog"
      :setting="dataSetting"
      :currentPage="dataSetting.currentPage"
      @changePageSize="replacePageSize"
      @changePageNumber="replacePageNumber"
      @goToDetail="goToDetail"
    />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import { postService } from '@/services';
import ListBlogCard from '@/components/shared/ListBlogCard.vue';
import { DataPageRequest, DataPageSetting, Utils } from '@/models'

@Component({
  components: {
    ListBlogCard,
  },
})
export default class PostListPage extends Vue {
  private data = [];
  private dataSetting: DataPageSetting = new DataPageSetting();
  private request: DataPageRequest = new DataPageRequest();
  private currentPage?: number = 0;
  private perPage?: number = 0;
  private isAdmin?: boolean = false;

  async created() {
    if(this.$router.currentRoute.path === "/admin/post") {
      this.isAdmin = true;
    }
    this.currentPage = this.$route.query.currentPage
      ? Number.parseInt(Utils.getQueryParam(this.$route.query.currentPage))
      : this.dataSetting.currentPage;

    this.perPage = this.$route.query.perPage
      ? Number.parseInt(Utils.getQueryParam(this.$route.query.perPage))
      : this.dataSetting.perPage;
    
    await postService.getAll(this.request).then(res => {
      this.data = res.data.data.content;
      this.dataSetting.totalPosts = res.data.data.totalElements;
      this.dataSetting.init(this.data);
    })
  }
  private replacePageSize(pageSize: number) {
    this.request.perPage = pageSize;
    this.request.currentPage = 1;
    postService.getAll(this.request).then(res => {
      this.data = res.data.data.content;
      this.dataSetting.totalPosts = res.data.data.totalElements;
      this.dataSetting.init(this.data);
    });
    // (this.$refs.listBlog as any).refreshTable();
    // this.$router.replace({
    //   name: this.replaceRouterName(),
    //   query: {
    //     currentPage: '1',
    //     pageSize: pageSize.toString(),
    //   }
    // });
  }

  private replacePageNumber(page: number) {
    this.request.currentPage = page;
    postService.getAll(this.request).then(res => {
      this.data = res.data.data.content;
      this.dataSetting.totalPosts = res.data.data.totalElements;
      this.dataSetting.init(this.data);
    });
    // (this.$refs.listBlog as any).refreshTable();
    // this.$router.replace({
    //   name: this.replaceRouterName(),
    //   query: {
    //     currentPage: page.toString(),
    //     pageSize: this.$route.query.perPage
    //   ? Number.parseInt(Utils.getQueryParam(this.$route.query.perPage)).toString()
    //   : this.dataSetting.perPage?.toString(), 
    //   }
    // });
  }

  private goToDetail(id: any) {
    this.$router.push({
      name: "routes.post_edit",
      params: {id: id}
    })
  }

  private replaceRouterName() {
    return 'home';
  }

  private addNewPost() {
    this.$router.push({
      name: 'routes.post_add'
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