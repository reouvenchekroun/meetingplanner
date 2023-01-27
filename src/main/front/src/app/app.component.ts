import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  title = 'AngularHttpRequest';

  constructor(private http: HttpClient) {

  }

  onMeetingCreate(meeting: {name: string, type: string, date: string, start: number, end: number, attendees: number}) {
    console.log(meeting);
    this.http.post('http://localhost:8080/find-meeting-room', meeting).subscribe((res) => {
      console.log(res);
    });
  }
}


