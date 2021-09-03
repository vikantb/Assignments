import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BlogService,Blog } from '../blog.service';

@Component({
  selector: 'app-show-blog',
  templateUrl: './show-blog.component.html',
  styleUrls: ['./show-blog.component.css']
})
export class ShowBlogComponent implements OnInit {

  error:string="";
  blog:Blog

  constructor(private blogService: BlogService,private route: ActivatedRoute,private router:Router) { 
      this.blog = new Blog(0,"","","");
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => { 
          let id= params.get("id");
          this.blogService.getBlog(id).subscribe((blog)=> {this.blog=blog;},
                                                 (error)=>{this.router.navigate(['/home'])});
     });
    
  }

  delete(id:any){
    this.blogService.delete(id).subscribe((blog)=> {this.router.navigate(['/home'])},
                                          (error)=>{this.error=error.message});
  }

}
