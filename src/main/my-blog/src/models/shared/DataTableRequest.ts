export class DataTableRequest {
  public currentPage?: number = 1;
  public perPage?: number = 10;
  public filter: any = '';
  public sortBy?: string = '';
  public sortDesc?: boolean = false;
  // For dynamic properties (Loose object)
  [key: string]: any;

  constructor(init?: Partial<DataTableRequest>) {
    Object.assign(this, init);
  }
}
