import {AfterViewInit, Component, OnInit, viewChild, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {Router} from "@angular/router";

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit , AfterViewInit {
  public students: any;
  public  dataSource : any ;
  public displayedColumns : string[] = ["id","firstName","lastName","payments"];
  @ViewChild(MatPaginator) paginator !: MatPaginator;
  //@ViewChild : C’est un décorateur Angular utilisé pour lier une variable TypeScript
  // à un élément du DOM ou à un composant enfant dans le template HTML
  //(MatPaginator) :C’est le type de composant qu’on veut récupérer.
  // !: Je promets à TypeScript que cette variable ne sera
  // jamais null ou undefined au moment où je l’utiliserai.
  // Je promets qu’il existera bien une fois le HTML chargé
  @ViewChild(MatSort) sort!: MatSort;


  constructor(private router: Router) {}

  ngOnInit(): void {
    this.students = [];
    for (let i: number = 1; i < 100; i++) {
      this.students.push({
        id: i,
        firstName: Math.random().toString(20).substring(2, 8),
        lastName: Math.random().toString(20).substring(2, 8)

      });
    }
    this.dataSource = new MatTableDataSource(this.students)
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  //filterStudents($event: Event):void {
  //  let value: HTMLInputElement.value = event.target as HTMLInputElement.value;
  //  this.dataSource.filter = value;

  filterStudents(event: Event): void {
    const value: string = (event.target as HTMLInputElement).value;
    this.dataSource.filter = value.trim().toLowerCase();
  }

  getPayment(student:any):void {
    this.router.navigateByUrl("/payments")

  }
}
