import { Component, OnInit } from '@angular/core';

import { Sector } from '../../models/Sector';
import { SectorService } from '../../services/sector.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-sectors',
  templateUrl: './sectors.component.html',
  styleUrls: ['./sectors.component.css']
})
export class SectorsComponent implements OnInit {

  sectors: Sector[];

  constructor(private sectorService: SectorService, private router: Router) { }

  ngOnInit(): void {
    this.sectorService.getSectors()
      .subscribe(response => {
        this.sectors = response;
      });
    //this.getSectors();
  }

  onDeleteClick(id: string) {
    const currurl = this.router.url;
    this.sectorService.deleteSector(id);
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate([currurl]);
    this.getSectors();
  }

  getSectors(){
    this.sectorService.getSectors()
    .subscribe(response => {
      this.sectors = response;
    });
  }
}
