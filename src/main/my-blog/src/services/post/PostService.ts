import Vue from 'vue';
import { AxiosPromise } from 'axios';
import { DataPageRequest, Response, Post } from '@/models';

class PostService extends Vue {
  private ROOT_API = process.env.VUE_APP_ROOT_API + '/webapi/post';

  public getAll(request: DataPageRequest): AxiosPromise<any> {
    return Vue.axios.get<any>(this.ROOT_API, {params: request});
  }

  public get(id: string): AxiosPromise<Response<Post>> {
    return Vue.axios.get<Response<Post>>(this.ROOT_API + '/' + id);
  }

  public update(data: Post): AxiosPromise<Response<Post>> {
    return Vue.axios.put<Response<Post>>(this.ROOT_API + '/' + data.id, data);
  }

  public save(data: Post): AxiosPromise<Response<Post>> {
    return Vue.axios.post<Response<Post>>(this.ROOT_API, data);
  }
  
}
export const postService = new PostService();
