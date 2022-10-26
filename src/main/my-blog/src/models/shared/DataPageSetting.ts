export class DataPageSetting {
  public static init(items: any[] | any): DataPageSetting {
    const setting = new DataPageSetting(items);
    return setting;
  }
  
  public items: any = [];
  public totalPosts?: number = 0;
  public currentPage?: number = 1;
  public perPage?: number = 15;
  public pageOptions: number[] = [10, 20, 30, 50];

  constructor(init?: Partial<DataPageSetting>) {
    Object.assign(this, init);
  }

  public init(items: any[] | any) {
    this.items = items;
  }
}
  