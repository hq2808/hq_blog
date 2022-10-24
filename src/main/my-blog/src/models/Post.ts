export class Post {
  public id?: string;
  public slug?: string;
  public title?: string;
  public content?: string;
  public image?: string;
  public status?: number;
  public user: any;
  public createdAt: any;

  constructor(init?: Post) {
    Object.assign(this, init);
  }
}
