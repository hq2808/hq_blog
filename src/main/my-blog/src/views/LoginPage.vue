<template>
  <section class="vh-100" style="background-color: pink">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
          <div class="card shadow-2-strong" style="border-radius: 1rem;">
            <div class="card-body p-5 text-center">

              <h3 class="mb-5">Sign in</h3>

              <div class="form-outline mb-4">
                <input type="email" id="typeEmailX-2" class="form-control form-control-lg" placeholder="Username" v-model="username" />
              </div>

              <div class="form-outline mb-4">
                <input type="password" id="typePasswordX-2" class="form-control form-control-lg" placeholder="Password" v-model="password" />
              </div>
              <div>
                <!-- <span v-if="isError">{{ $t('view.login.msg_error') }}</span>
                <span v-if="isServerError">{{ $t('view.login.msg_internal_error') }}</span>
                <span v-if="isUnauthorized">msg_unauthorized</span>
                <span v-if="isPasswordError">msg_password_not_valid</span> -->
              </div>
              <!-- Checkbox -->
              <div class="form-check d-flex justify-content-start mb-4">
                <input class="form-check-input" type="checkbox" value="" id="form1Example3" v-model="checked" />
                <label class="form-check-label" for="form1Example3"> Remember password </label>
              </div>
              <button class="btn btn-light" @click="login" type="submit">Login</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
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
  private checked?: boolean = false;

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