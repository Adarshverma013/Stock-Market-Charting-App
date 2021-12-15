import { Component, OnInit } from '@angular/core';

import { Sector } from '../../../models/Sector';
import { SectorService } from '../../../services/sector.service';
import { NgForm} from '@angular/forms';
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-sector',
  templateUrl: './create-sector.component.html',
  styleUrls: ['./create-sector.component.css']
})
export class CreateSectorComponent implements OnInit {

  public sector: Sector = {
    name: '',
    description: ''
  };

  constructor(private sectorService: SectorService, private router: Router) { }

  ngOnInit(): void {
    console.log('into create sector');
  }

  public onSubmit(myform: NgForm) {
      this.sectorService.addSector(myform.value);
      myform.reset();
      this.router.navigate(['/sectors']);
  }
}
