export class LoginDetails {
  public email?: string;
  public isLoginSuccess?: boolean;

  constructor(init?: LoginDetails) {
    Object.assign(this, init);
  }
}
