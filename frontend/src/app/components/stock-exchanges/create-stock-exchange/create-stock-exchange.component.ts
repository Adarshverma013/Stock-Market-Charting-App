import { Component, OnInit } from '@angular/core';

import { StockExchange } from '../../../models/StockExchange';
import {StockExchangeService} from "../../../services/stock-exchange.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-create-stock-exchange',
  templateUrl: './create-stock-exchange.component.html',
  styleUrls: ['./create-stock-exchange.component.css']
})
export class CreateStockExchangeComponent implements OnInit {

  stockExchange: StockExchange = {
    name: '',
    description: '',
    address: '',
    remarks : ''
  };

  constructor(private stockExchangeService: StockExchangeService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(myForm: NgForm) {
    this.stockExchangeService.addStockExchange(myForm.value);
    myForm.resetForm();
    this.router.navigate(['/stock-exchanges']);
  }
}
