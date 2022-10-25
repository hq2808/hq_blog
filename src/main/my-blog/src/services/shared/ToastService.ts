import Vue from 'vue';
import mainApp from '@/main';
// import {ResponseCodeEnum} from '@/models';

class ToastService extends Vue {

  public success(messenger: string) {
    Vue.toasted.success(messenger);
  }

  public error(messenger: string) {
    Vue.toasted.error(messenger);
  }

  // public error(code: number, messenger?: string) {
  //   let showMessage = '';
  //   if (code === ResponseCodeEnum.CODE_PERMISSION) {
  //     showMessage = mainApp.$t('response.code_permission').toString();
  //   } else if (code === ResponseCodeEnum.CODE_NOT_FOUND) {
  //     showMessage = mainApp.$t('response.code_not_found').toString();
  //   } else if (code === ResponseCodeEnum.CODE_ALREADY_EXIST) {
  //     showMessage = mainApp.$t('response.code_already_exist').toString();
  //   } else if (code === ResponseCodeEnum.CODE_LIKE_OLD_PASSWORD) {
  //     showMessage = mainApp.$t('response.code_like_old_password').toString();
  //   } else if (code === ResponseCodeEnum.CODE_EXPIRED) {
  //     showMessage = mainApp.$t('response.code_expired').toString();
  //   } else if (code === ResponseCodeEnum.CODE_INTERNAL_ERROR) {
  //     showMessage = mainApp.$t('response.code_internal_error').toString() + (messenger ? ' - ' + mainApp.$t(messenger).toString() : '');
  //   } else if (code === ResponseCodeEnum.CODE_BUSINESS) {
  //     showMessage = (messenger ? mainApp.$t(messenger).toString() : '');
  //   }
  //   mainApp.$toasted.error(showMessage);
  // }

  public errorMessage(messenger: string) {
    const msg = messenger.toString();
    mainApp.$toasted.error(msg, {
      position: "bottom-right", 
      duration : 1000
    });
  }

  public successMessage(messenger: string) {
    const msg = messenger.toString();
    mainApp.$toasted.success(msg, {
      position: "bottom-right", 
      duration : 1000
    });
  }

  public infoMessage(messenger: string) {
    const msg = messenger.toString();
    mainApp.$toasted.info(msg, {
      position: "bottom-right", 
      duration : 1000
    });
  }

}

export const toastService = new ToastService();
