import { Component, OnInit } from '@angular/core';
import { Item } from '../services/item';
import { ItemService } from '../services/item.service';

@Component({
  selector: 'cart-app',
  templateUrl: './cart.component.html', 
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit { 
   cartItems: Item[] = [];
   constructor(private itemService: ItemService) { }
   getItemsForCart(): void {
        this.cartItems = this.itemService.getSelectedItems();
		console.log(this.cartItems);
   }
   getTotalAmount():number {
    let totalAmount:number=0;
    this.cartItems.map(item=><number>item.price).forEach(val=>totalAmount+=val);
    return totalAmount;
   }
   ngOnInit(): void {
        this.getItemsForCart();
   }
   removeItemFromCart(id:number): void {
        this.itemService.removeItem(id);
   }
}
    