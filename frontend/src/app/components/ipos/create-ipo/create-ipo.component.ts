import { Component, OnInit } from '@angular/core';

import { IPO } from '../../../models/IPO';
import { IpoService } from 'src/app/services/ipo.service';
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-create-ipo',
  templateUrl: './create-ipo.component.html',
  styleUrls: ['./create-ipo.component.css']
})
export class CreateIpoComponent implements OnInit {

  ipo: IPO = {
    companyName: '',
    stockExchangeName: '',
    price: 0,
    shares: 0,
    openDateTime: '',
    remarks: ''
  };

  constructor(private ipoService: IpoService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(myForm: NgForm) {

      this.ipoService.addIpo(myForm.value);
      myForm.resetForm();
      this.router.navigate(['/ipos']);
  }

}
