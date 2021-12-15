import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { StockExchange } from 'src/app/models/StockExchange';
import { StockExchangeService } from 'src/app/services/stock-exchange.service';

@Component({
  selector: 'app-edit-stock-exchanges',
  templateUrl: './edit-stock-exchanges.component.html',
  styleUrls: ['./edit-stock-exchanges.component.css']
})
export class EditStockExchangesComponent implements OnInit {


  id: string;
  stockExchange: StockExchange;

  constructor(private stockExchangeService:StockExchangeService ,
    private route: ActivatedRoute,
     private router: Router) { }

  ngOnInit(): void {
    console.log('into edit sector');
    this.id = this.route.snapshot.params['id'];

    this.stockExchangeService.getStockExchange(this.id).subscribe(data => {
      this.stockExchange = data;
    }, error => console.log(error));

  }

  public onSubmit(myform: NgForm) {
    this.stockExchangeService.updateStockExchange({...myform.value,id:this.id});
    this.router.navigate(['/sectors']);
}

}
