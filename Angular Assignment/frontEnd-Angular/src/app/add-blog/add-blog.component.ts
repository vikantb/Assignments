import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BlogService,Blog } from '../blog.service';

@Component({
  selector: 'app-add-blog',
  templateUrl: './add-blog.component.html',
  styleUrls: ['./add-blog.component.css']
})
export class AddBlogComponent implements OnInit {

  error:string="";

  constructor(private blogService: BlogService,private router:Router) { }
  
  ngOnInit(): void {
  }

  submitForm(blog:Blog){
    this.blogService.addBlog(blog).subscribe(blog => {this.router.navigate(['/home'])},
                                              (err) => {this.error=err.message});
  }

}
