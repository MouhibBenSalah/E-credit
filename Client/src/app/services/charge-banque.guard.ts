import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';

export const chargeBanqueGuard: CanActivateFn = (route, state) => {
  const auth = inject(AuthService);
   const router = inject(Router)
   if (auth.isLoggedIn() && auth.currentUser()?.role === 'CHARGE_BANQUE' ) {
     return true ;
   }
   else {
     router.navigate(['/login']);
     return false ;
   }
  };