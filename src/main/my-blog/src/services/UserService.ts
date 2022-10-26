import Vue from 'vue';
import { AxiosPromise } from 'axios';
import {
  Response,
  UserDetail,
} from '@/models';

class UserService extends Vue {
  private ROOT_URL: string = process.env.VUE_APP_ROOT_API + '/webapi/user';

  public getUserDetail(): AxiosPromise<Response<UserDetail>> {
    return Vue.axios.get<Response<UserDetail>>(this.ROOT_URL + '/user-detail');
  }
}
export const userService = new UserService();