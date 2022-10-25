export class LoginDetails {
  public email?: string;
  public isLogin?: boolean;
  public isLoginSuccess?: boolean;

  constructor(init?: LoginDetails) {
    Object.assign(this, init);
  }
}
