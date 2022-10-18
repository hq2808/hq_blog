export class UserDetail {
  public username?: string;
  public role?: string;
  public displayName?: string;

  constructor(init?: UserDetail) {
    Object.assign(this, init);
  }
}
