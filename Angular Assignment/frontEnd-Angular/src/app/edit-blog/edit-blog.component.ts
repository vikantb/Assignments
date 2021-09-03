
import { Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BlogService,Blog } from '../blog.service';

@Component({
  selector: 'app-edit-blog',
  templateUrl: './edit-blog.component.html',
  styleUrls: ['./edit-blog.component.css']
})
export class EditBlogComponent implements OnInit {

  blog:Blog;
  error:string="";

  constructor(private blogService: BlogService,private route:ActivatedRoute,private router:Router) { 
            this.blog=new Blog(1,"","","");
  }

  ngOnInit(): void {
      this.route.paramMap.subscribe(params => { 
          let id= params.get("id");
          this.blogService.getBlog(id).subscribe((blog)=> {this.blog=blog;},
                                                (error)=>{this.router.navigate(['/home'])});
      });
  }

  submitForm(id:number,blog:Blog){
    this.blogService.editBlog(id,blog).subscribe(blog => {this.router.navigate(['/home'])},
                                                (err) => {this.error=err.message});
  }

}

