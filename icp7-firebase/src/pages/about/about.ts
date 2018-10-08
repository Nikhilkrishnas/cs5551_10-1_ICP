import { Component } from '@angular/core';
import { NavController, ToastController } from 'ionic-angular';
import { AngularFireAuth } from 'angularfire2/auth';
import { AngularFireDatabase } from 'angularfire2/database';

@Component({
  selector: 'page-about',
  templateUrl: 'about.html'
})
export class AboutPage {
  firstname: string = "";
  lastname: string = "";
  username: string = "";

  constructor(public navCtrl: NavController,
    public fireDb: AngularFireDatabase,
    private toastCtrl: ToastController,
    public fire: AngularFireAuth) {

  }

  save() {
    this.fire.authState.subscribe(auth => {
      let user = {
        "username": this.username,
        "firstname": this.firstname,
        "lastname": this.lastname
      }
      if (auth.uid !== null) {
      this.fireDb.list(`profile/${auth.uid}`).push(user)
        .then(() => {
          this.presentToast("User Saved.");
        });
      }
    })
  }
  /*
      This method will show the Toast messages.
    */
  presentToast(msg) {
    let toast = this.toastCtrl.create({
      message: msg,
      duration: 3000,
      position: 'top'
    });

    toast.onDidDismiss(() => {
      console.log('Dismissed toast');
    });

    toast.present();
  }
}
