import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Company } from 'src/app/models/Company';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-edit-company',
  templateUrl: './edit-company.component.html',
  styleUrls: ['./edit-company.component.css']
})
export class EditCompanyComponent implements OnInit {

  id: string;
  company: Company;

  constructor(private companyService: CompanyService,
    private route: ActivatedRoute,
     private router: Router) { }

  ngOnInit(): void {
    console.log('into edit sector');
    this.id = this.route.snapshot.params['id'];

    this.companyService.getCompany(this.id).subscribe(data => {
      this.company = data;
    }, error => console.log(error));

  }

  public onSubmit(myform: NgForm) {
    this.companyService.updateCompany({...myform.value,id:this.id});
    this.router.navigate(['/sectors']);
  }

}
