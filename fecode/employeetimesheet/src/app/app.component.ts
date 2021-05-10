import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Employee } from './modal/employee';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap'; 

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  closeResult = ''; 
  employees : Employee[] = [];
  vacations = null;
  workDays = null;
  selectedEmployee : Employee;
  modalReference: any;
  constructor(private http: HttpClient, private modalService: NgbModal) { }

  ngOnInit() {
    this.http.get<Employee[]>('api/v1/employees').subscribe(data => {
            this.employees = data;
    });
  }

  logWork(){
	this.modalReference.close();
	const params = new HttpParams()
	.set('id', this.selectedEmployee.id.toString())
    .set('workDays', this.workDays.toString());
    this.http.post<any>('api/v1/work', params).subscribe(data => {
      this.employees = data;
	  this.vacations = null;
      this.workDays = null;
      this.selectedEmployee = null;
    }, error => {
	  this.vacations = null;
      this.selectedEmployee = null;
      this.workDays = null;
	  if(error.status == 500){
		window.alert("Internal Server error.")
	  } else if (error.status == 400){
		window.alert("Work days value should be in between range of 1 to 260");
	  }
	});
  }

  takeVacation(){
	this.modalReference.close();
	const params = new HttpParams()
	.set('id', this.selectedEmployee.id.toString())
    .set('vacationDays', this.vacations.toString());
    this.http.post<any>('api/v1/vacation', params).subscribe(data => {
      this.employees = data;
	  this.vacations = null;
	  this.workDays = null;
      this.selectedEmployee = null;
    },
	error => {
	  this.vacations = null;
      this.selectedEmployee = null;
      this.workDays = null;
	  if(error.status == 500){
		window.alert("Internal Server error.")
	  } else if (error.status == 400){
		window.alert("You do not have enough vacations to take.");
	  }
	});
  }

  open(content :any, employee: Employee) { 
	this.selectedEmployee = employee;
	this.modalReference = this.modalService.open(content, 
   {ariaLabelledBy: 'modal-basic-title'});
    this.modalReference.result.then((result) => { 
      this.closeResult = `Closed with: ${result}`; 
    }, (reason) => { 
      this.closeResult =  
         `Dismissed ${this.getDismissReason(reason)}`; 
    }); 
  } 
  
  private getDismissReason(reason: any): string { 
    if (reason === ModalDismissReasons.ESC) { 
      return 'by pressing ESC'; 
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) { 
      return 'by clicking on a backdrop'; 
    } else { 
      return `with: ${reason}`; 
    } 
  } 
}
