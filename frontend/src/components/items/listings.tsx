export class Item {
    id!: number;
    name: string = '';
    description: string = '';
    imageUrl: string = '';
    seller: string = '';
    listingDate: Date = new Date();
    price: number = 0;
    isActive: boolean = false;
    get isNew(): boolean {
      return this.id === undefined;
    }
  
    constructor(initializer?: any) {
      if (!initializer) return;
      if (initializer.id) this.id = initializer.id;
      if (initializer.name) this.name = initializer.name;
      if (initializer.description) this.description = initializer.description;
      if (initializer.imageUrl) this.imageUrl = initializer.imageUrl;
      if (initializer.seller)
        this.seller = initializer.seller;
      if (initializer.listingDate)
        this.listingDate = new Date(initializer.listingDate);
      if (initializer.price) this.price = initializer.price;
      if (initializer.isActive) this.isActive = initializer.isActive;
    }
  }