import Vue from 'vue';
import { AxiosPromise } from 'axios';
import { DataPageRequest } from '@/models';
class PostService extends Vue {
  private ROOT_API = process.env.VUE_APP_ROOT_API + '/webapi/post';

  public get(request: DataPageRequest): AxiosPromise<any> {
    return Vue.axios.get<any>(this.ROOT_API, {params: request});
  }
  
}
export const postService = new PostService();
