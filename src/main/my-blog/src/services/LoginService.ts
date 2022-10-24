import Vue from 'vue';
// import jwt_decode from 'jwt-decode';

import { LoginDetail } from '@/models';

class LoginService extends Vue {
  private ROOT_URL: string = process.env.VUE_APP_ROOT_API + '/auth/login';
  private token = 'x-auth-token';
  private userDetailKey?: string = 'user-detail';

  public isAuthenticated(): boolean {
    return localStorage[this.token] !== null
      && localStorage[this.token] !== undefined
      && localStorage[this.token] !== ''
      && localStorage[this.token] !== 'null';
  }
  
  public addToken(config: any): any {
    if (localStorage[this.token]) {
      config.headers[this.token] = localStorage[this.token];
    }
    return config;
  }

  public login(request: any): Promise<any> {
    return Vue.axios.post(this.ROOT_URL, request)
      .then((res) => {
        if (res.headers['x-auth-token']) {
          // Success
          const decoded: any = res.headers['x-auth-token'];
          if (decoded.isLogin) {
            localStorage[this.token] = res.headers['x-auth-token'];
            return new LoginDetail({
              isLogin: true,
              isLoginSuccess: true,
            });
          } else {
            localStorage[this.token] = res.headers['x-auth-token'];
            return new LoginDetail({
              email: decoded.username,
              isLogin: false,
              isLoginSuccess: true,
            });
          }
        }
        return new LoginDetail({
          // isLoginSuccess: false,
          
          // Set auto login
          isLoginSuccess: true,
          isLogin: true,
        });
      });
  }

  public logout(): Promise<any> {
    // TODO: Server-side token handler
    localStorage[this.token] = '';
    // localStorage[this.userDetailKey] = '';
    return Promise.resolve(true);
  }
}
export const loginService = new LoginService();