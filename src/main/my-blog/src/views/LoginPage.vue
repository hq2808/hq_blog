<template>
  <div class="app flex-row align-items-center">
    <div class="container">
      <b-row class="justify-content-center">
        <b-col md="6">
          <!-- <div class="logo-container">
            <img src="@/assets/logo_2.jpg" width="250" alt="Logo">
          </div> -->
          <b-card-group>
            <b-card no-body class="p-4">
              <b-card-body>
                <b-form v-on:keyup.enter="login()">
                  <b-row>
                    <b-col>
                      <!-- <h2 align="center" class="mb-4"><strong>{{ $t('view.login.title') }}</strong></h2> -->
                    </b-col>
                  </b-row>
                  <p class="text-muted">Login</p>
                  <b-input-group class="mt-3">
                    <b-input-group-prepend><b-input-group-text><b-icon icon="person-fill"></b-icon></b-input-group-text></b-input-group-prepend>
                    <b-form-input
                      type="text"
                      v-model="username"
                      placeholder="Username"
                      @keyup.enter="login"
                      @input="resetError"
                      autocomplete="username email"
                      autocorrect="off"
                      autocapitalize="none"
                      class="form-control"/>
                  </b-input-group>
                  <b-input-group class="mt-3">
                    <b-input-group-prepend><b-input-group-text><b-icon icon="lock-fill"></b-icon></b-input-group-text></b-input-group-prepend>
                    <b-form-input 
                      type="password"
                      v-model="password"
                      placeholder="password"
                      @keyup.enter="login"
                      @input="resetError"
                      autocomplete="current-password"
                      autocorrect="off"
                      autocapitalize="none"
                      class="form-control"/>
                  </b-input-group>
                  <b-row class="mt-3">
                    <b-col>
                      <div v-if="isLoading" class="loader"></div>
                      <b-alert :show="isError || isServerError || isUnauthorized || isPasswordError" variant="danger">
                        <span v-if="isError">msg_error</span>
                        <span v-if="isServerError">msg_internal_error</span>
                        <span v-if="isUnauthorized">msg_unauthorized</span>
                        <span v-if="isPasswordError">msg_password_not_valid</span>    
                      </b-alert>
                    </b-col>
                  </b-row>
                  <b-row class="mt-4">
                    <b-col cols="12" class="text-center">
                      <b-button @click="login" class="login-btn btn-main color-admin">Login</b-button>
                    </b-col>
                    <b-col cols="12" class="text-center mt-3">
                      <b-button @click="goToForgetPassword" variant="link" class="px-0">Forget password</b-button>
                    </b-col>
                  </b-row>               
                </b-form>
              </b-card-body>
            </b-card>
          </b-card-group>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import { loginService, userService} from '@/services';
import { LoginDetails } from '@/models';
import store from '../../src/store';

@Component
export default class LoginPage extends Vue {
  private isLoading?: boolean = false;
  private isError?: boolean = false;
  private isServerError?: boolean = false;
  private isUnauthorized?: boolean = false;
  private isPasswordError?: boolean = false;
  private username?: string = '';
  private password?: string = '';

  private created() {
    this.$store.dispatch('setUser', {});
  }

  private login() {
    this.resetError();
    const loginData = {
      username: this.username,
      password: this.password,
    };
    this.isLoading = true;
    loginService.login(loginData)
      .then((data: LoginDetails) => {
        if (data && data.isLoginSuccess) {
          // add use in store
          userService.getUserDetail().then((res: any) => {
          const user = res.data.data;
          if (user && user.role) {
            store.dispatch('setUser', user);
            store.dispatch('setTimeSignin', new Date());
          }
        });
        // router to List post
        this.$router.push({ name: 'routes.admin' });
        }
      })
      .catch((error) => {
        if (error.response && error.response.status === 401) {
          this.isUnauthorized = true;
        } else if (error.response && error.response.status === 500) {
          this.isServerError = true;
        } else {
          this.isError = true;
        }
      })
      .finally(() => this.isLoading = false);
  }

  private checkPassword(password: string): boolean {
    return /^[a-zA-Z0-9!@#$%^&*]{8,64}$/g.test(password);
  }

  private resetError() {
    this.isError = false;
    this.isServerError = false;
    this.isUnauthorized = false;
    this.isPasswordError = false;
  }

  private goToForgetPassword() {
    this.$router.push({ name: 'router.forget_password' });
  }
}
</script>
<style lang="scss" scoped>
  .logo-container {
    margin-bottom: 50px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .login-btn {
    width: 100%;
    font-size: 2rem;
  }
</style>