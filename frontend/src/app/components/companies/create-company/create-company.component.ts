import { Component, OnInit } from '@angular/core';

import { Company } from '../../../models/Company';
import { CompanyService } from 'src/app/services/company.service';
import {NgForm} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent implements OnInit {

  company: Company = {
    name: '',
    code: '',
    turnover: '',
    ceo: '',
    boardOfDirectors: '',
    stockExchangeNames: '',
    sectorName: '',
    description: ''
  };

  constructor(private companyService: CompanyService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(myForm: NgForm): void {
    this.companyService.addCompany(myForm.value);
    myForm.resetForm();
    this.router.navigate(['/companies']);
  }
}
