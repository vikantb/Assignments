import { Component, OnInit} from '@angular/core';
import { BlogService,Blog } from '../blog.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  blogs:Blog[]=[] ; 
  error:string ="";

  constructor(private blogService: BlogService){}

  ngOnInit():void{
    this.blogService.getBlogs()
      .subscribe( (data) => {
                            this.blogs=data;
                            this.blogs.sort((a, b)=>b.id-a.id);
                            if(this.blogs.length===0){
                              this.error="No Blog is Available please Add some Blogs...."
                            }
                          },
                  (err) => {this.error=err.message} );
  }
}
