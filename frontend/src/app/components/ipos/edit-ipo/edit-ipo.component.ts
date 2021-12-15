import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IPO } from 'src/app/models/IPO';
import { IpoService } from 'src/app/services/ipo.service';

@Component({
  selector: 'app-edit-ipo',
  templateUrl: './edit-ipo.component.html',
  styleUrls: ['./edit-ipo.component.css']
})
export class EditIpoComponent implements OnInit {

  id: string;
  ipo: IPO;

  constructor(private ipoService: IpoService,
    private route: ActivatedRoute,
     private router: Router) { }

  ngOnInit(): void {
    console.log('into edit sector');
    this.id = this.route.snapshot.params['id'];

    this.ipoService.getIpo(this.id).subscribe(data => {
      this.ipo = data;
    }, error => console.log(error));

  }

  public onSubmit(myform: NgForm) {
    this.ipoService.updateIpo({...myform.value,id:this.id});
    this.router.navigate(['/sectors']);
  }

}
