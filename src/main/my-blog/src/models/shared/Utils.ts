export class Utils {
  public static getQueryParam(param?: string | null | Array<(string | null)>): string {
    if (param) {
      return (typeof param === 'string') ? param : this.getQueryParam(param[0]);
    }
    return '';
  }
}