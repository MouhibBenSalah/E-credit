import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';

export const chefAgenceGuard: CanActivateFn = (route, state) => {
  const auth = inject(AuthService);
   const router = inject(Router)
   if (auth.isLoggedIn() && auth.currentUser()?.role === 'CHEF_AGENCE' ) {
     return true ;
   }
   else {
     router.navigate(['/login']);
     return false ;
   }
  };