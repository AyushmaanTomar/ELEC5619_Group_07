export class Item {
    user: any = null;
    id!: number;
    name: string = '';
    price: number = 0;
    qty: number = 1;
    likeAmount: number = 0;
    description: string = '';
    listingDate: Date = new Date();
    imagePath: string = '';
    sold: boolean = false;
    get isNew(): boolean {
      return this.id === undefined;
    }
  
    constructor(initializer?: any) {
      if (!initializer) return;
      if (initializer.id) this.id = initializer.id;
      if (initializer.name) this.name = initializer.name;
      if (initializer.description) this.description = initializer.description;
      if (initializer.imageUrl) this.imagePath = initializer.imagePath;
      if (initializer.user)
        this.user = initializer.user;
      if (initializer.listingDate)
        this.listingDate = new Date(initializer.listingDate);
      if (initializer.price) this.price = initializer.price;
      if (initializer.isActive) this.sold = initializer.isActive;
    }
  }