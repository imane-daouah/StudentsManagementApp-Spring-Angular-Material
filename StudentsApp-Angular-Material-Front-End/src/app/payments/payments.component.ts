import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatTableDataSource} from "@angular/material/table"; // ✅ HttpClient au lieu de HttpClientModule

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent implements OnInit {

  payments: any;
  public  dataSource : any ;

  public displayedColumns = ['id','date','amount','type','status','firstName'];
  constructor(private http: HttpClient) { } // ✅ HttpClient injecté

  ngOnInit() {
    this.http.get("http://localhost:8787").subscribe({
      next: (data: Object) => {
        this.payments = data;
        this.dataSource = new MatTableDataSource(this.payments)
      },
      error: (err: any) => {
        console.error(err);
      }
    });
  }
}
