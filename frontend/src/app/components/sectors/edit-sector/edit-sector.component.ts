import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Sector } from 'src/app/models/Sector';
import { SectorService } from 'src/app/services/sector.service';

@Component({
  selector: 'app-edit-sector',
  templateUrl: './edit-sector.component.html',
  styleUrls: ['./edit-sector.component.css']
})
export class EditSectorComponent implements OnInit {

  id: string;
  sector: Sector;

  constructor(private sectorService: SectorService,
    private route: ActivatedRoute,
     private router: Router) { }

  ngOnInit(): void {
    console.log('into edit sector');
    this.id = this.route.snapshot.params['id'];

    this.sectorService.getSector(this.id).subscribe(data => {
      this.sector = data;
    }, error => console.log(error));

  }

  public onSubmit(myform: NgForm) {
    this.sectorService.updateSector({...myform.value,id:this.id});
    this.router.navigate(['/sectors']);
}

}
