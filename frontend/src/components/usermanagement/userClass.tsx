import { Item } from "../items/listings";

export class User {
    username: number | undefined;
    name: string = '';
    imageUrl: string = '';
    selling: Array<Item> = [];
    constructor(initializer?: any) {
      if (!initializer) return;
      if (initializer.username) this.username = initializer.username;
      if (initializer.name) this.name = initializer.name;
      if (initializer.imageUrl) this.imageUrl = initializer.imageUrl;
      if (initializer.selling) {
        this.selling = initializer.selling.map((item: any) => new Item(item));
      }
    }
  }