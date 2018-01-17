public class Shopper {
    public GroceryItem[] cart;
    public int NumItems;
    public static final int MAX_CART_ITEMS = 100;


    public Shopper(){
        cart = new GroceryItem[MAX_CART_ITEMS];
        NumItems=0;
    }

    public GroceryItem[] getCart() {return cart;}

    public int getNumItems() {return NumItems;}

    public String toString(){
        return("Shopper with shopping cart containing " + NumItems + " items");
    }
    public void addItem(GroceryItem item){
        if((this.NumItems<MAX_CART_ITEMS)){
            this.cart[NumItems++]=item;
        }

    }
    public void removeItems(GroceryItem item){
        if(this.NumItems>0){
            for(int i = 0;i<this.NumItems;i++){
                if(this.cart[i].getName()==item.getName()){
                    GroceryItem tmp = this.cart[i];
                    this.cart[i]=this.cart[this.NumItems-1]; 
                    this.cart[this.NumItems-1]=tmp;
                    this.cart[this.NumItems-1]=null;
                    break;
                }
            }
        }                    this.NumItems--;
    }
    /*public GroceryBag[] packBags(){
        GroceryBag[] finalbags = new GroceryBag[getNumItems()];
        GroceryBag bag =  new GroceryBag();
        int index = 0;
        for(int i=0;i<NumItems;i++ ){
            if(cart[i].getWeight()< GroceryBag.getMaxWeight()){ //check if item not heavier than bag
                if(bag.getNumItems() + 1 < GroceryBag.getMaxItems()){ // if bag allows more item to add
                    if(bag.getWeight() + cart[i].getWeight() < GroceryBag.getMaxWeight()){
                       bag.addItem(cart[i]);  //add to bag
                       bag.removeItem(cart[i]);// then remove from cart

                    }else{
                        finalbags[index++] = bag;
                        bag = new GroceryBag();
                        }
                }else{
                    finalbags[index++] = bag;
                    bag = new GroceryBag();
                }

            }

        }GroceryBag[] copy = new GroceryBag[index];
        for(int i = 0;i<index;i++){
        	copy[i]=finalbags[i];
        }




        System.out.println("index is "+ index);
        return finalbags;

    }*/
	public GroceryBag[] packBags(){
		GroceryBag[] finalbags = createBags();

		int index = 0;
		for(int j = 0;j<MAX_CART_ITEMS;j++){


			for(int i = 0;i<this.getNumItems();i++){
					if((finalbags[j].getWeight()+this.cart[i].getWeight()<GroceryBag.getMaxWeight())&&(finalbags[0].getNumItems()<GroceryBag.getMaxItems())){
						finalbags[j].addItem(this.cart[i]);
						this.removeItems(this.cart[i]);

					
					}
			}
		}
		for(int j =0;j<MAX_CART_ITEMS;j++){
			if (finalbags[j].getWeight()==0.0){
				index = j-1;
				break;
			}
		}
		GroceryBag[] thecopy = new GroceryBag[index];
		for(int k=0;k<index;k++){
					thecopy[k]=finalbags[k];
		}

        return thecopy;

	}

public GroceryBag[] createBags(){
GroceryBag[] newbags = new GroceryBag[100];
GroceryBag bag1= new GroceryBag();
GroceryBag bag2= new GroceryBag();
GroceryBag bag3= new GroceryBag();
GroceryBag bag4= new GroceryBag();
GroceryBag bag5= new GroceryBag();
GroceryBag bag6= new GroceryBag();
GroceryBag bag7= new GroceryBag();
GroceryBag bag8= new GroceryBag();
GroceryBag bag9= new GroceryBag();
GroceryBag bag10= new GroceryBag();
GroceryBag bag11= new GroceryBag();
GroceryBag bag12= new GroceryBag();
GroceryBag bag13= new GroceryBag();
GroceryBag bag14= new GroceryBag();
GroceryBag bag15= new GroceryBag();
GroceryBag bag16= new GroceryBag();
GroceryBag bag17= new GroceryBag();
GroceryBag bag18= new GroceryBag();
GroceryBag bag19= new GroceryBag();
GroceryBag bag20= new GroceryBag();
GroceryBag bag21= new GroceryBag();
GroceryBag bag22= new GroceryBag();
GroceryBag bag23= new GroceryBag();
GroceryBag bag24= new GroceryBag();
GroceryBag bag25= new GroceryBag();
GroceryBag bag26= new GroceryBag();
GroceryBag bag27= new GroceryBag();
GroceryBag bag28= new GroceryBag();
GroceryBag bag29= new GroceryBag();
GroceryBag bag30= new GroceryBag();
GroceryBag bag31= new GroceryBag();
GroceryBag bag32= new GroceryBag();
GroceryBag bag33= new GroceryBag();
GroceryBag bag34= new GroceryBag();
GroceryBag bag35= new GroceryBag();
GroceryBag bag36= new GroceryBag();
GroceryBag bag37= new GroceryBag();
GroceryBag bag38= new GroceryBag();
GroceryBag bag39= new GroceryBag();
GroceryBag bag40= new GroceryBag();
GroceryBag bag41= new GroceryBag();
GroceryBag bag42= new GroceryBag();
GroceryBag bag43= new GroceryBag();
GroceryBag bag44= new GroceryBag();
GroceryBag bag45= new GroceryBag();
GroceryBag bag46= new GroceryBag();
GroceryBag bag47= new GroceryBag();
GroceryBag bag48= new GroceryBag();
GroceryBag bag49= new GroceryBag();
GroceryBag bag50= new GroceryBag();
GroceryBag bag51= new GroceryBag();
GroceryBag bag52= new GroceryBag();
GroceryBag bag53= new GroceryBag();
GroceryBag bag54= new GroceryBag();
GroceryBag bag55= new GroceryBag();
GroceryBag bag56= new GroceryBag();
GroceryBag bag57= new GroceryBag();
GroceryBag bag58= new GroceryBag();
GroceryBag bag59= new GroceryBag();
GroceryBag bag60= new GroceryBag();
GroceryBag bag61= new GroceryBag();
GroceryBag bag62= new GroceryBag();
GroceryBag bag63= new GroceryBag();
GroceryBag bag64= new GroceryBag();
GroceryBag bag65= new GroceryBag();
GroceryBag bag66= new GroceryBag();
GroceryBag bag67= new GroceryBag();
GroceryBag bag68= new GroceryBag();
GroceryBag bag69= new GroceryBag();
GroceryBag bag70= new GroceryBag();
GroceryBag bag71= new GroceryBag();
GroceryBag bag72= new GroceryBag();
GroceryBag bag73= new GroceryBag();
GroceryBag bag74= new GroceryBag();
GroceryBag bag75= new GroceryBag();
GroceryBag bag76= new GroceryBag();
GroceryBag bag77= new GroceryBag();
GroceryBag bag78= new GroceryBag();
GroceryBag bag79= new GroceryBag();
GroceryBag bag80= new GroceryBag();
GroceryBag bag81= new GroceryBag();
GroceryBag bag82= new GroceryBag();
GroceryBag bag83= new GroceryBag();
GroceryBag bag84= new GroceryBag();
GroceryBag bag85= new GroceryBag();
GroceryBag bag86= new GroceryBag();
GroceryBag bag87= new GroceryBag();
GroceryBag bag88= new GroceryBag();
GroceryBag bag89= new GroceryBag();
GroceryBag bag90= new GroceryBag();
GroceryBag bag91= new GroceryBag();
GroceryBag bag92= new GroceryBag();
GroceryBag bag93= new GroceryBag();
GroceryBag bag94= new GroceryBag();
GroceryBag bag95= new GroceryBag();
GroceryBag bag96= new GroceryBag();
GroceryBag bag97= new GroceryBag();
GroceryBag bag98= new GroceryBag();
GroceryBag bag99= new GroceryBag();
GroceryBag bag100= new GroceryBag();
newbags[0]=bag1;
newbags[1]=bag2;
newbags[2]=bag3;
newbags[3]=bag4;
newbags[4]=bag5;
newbags[5]=bag6;
newbags[6]=bag7;
newbags[7]=bag8;
newbags[8]=bag9;
newbags[9]=bag10;
newbags[10]=bag11;
newbags[11]=bag12;
newbags[12]=bag13;
newbags[13]=bag14;
newbags[14]=bag15;
newbags[15]=bag16;
newbags[16]=bag17;
newbags[17]=bag18;
newbags[18]=bag19;
newbags[19]=bag20;
newbags[20]=bag21;
newbags[21]=bag22;
newbags[22]=bag23;
newbags[23]=bag24;
newbags[24]=bag25;
newbags[25]=bag26;
newbags[26]=bag27;
newbags[27]=bag28;
newbags[28]=bag29;
newbags[29]=bag30;
newbags[30]=bag31;
newbags[31]=bag32;
newbags[32]=bag33;
newbags[33]=bag34;
newbags[34]=bag35;
newbags[35]=bag36;
newbags[36]=bag37;
newbags[37]=bag38;
newbags[38]=bag39;
newbags[39]=bag40;
newbags[40]=bag41;
newbags[41]=bag42;
newbags[42]=bag43;
newbags[43]=bag44;
newbags[44]=bag45;
newbags[45]=bag46;
newbags[46]=bag47;
newbags[47]=bag48;
newbags[48]=bag49;
newbags[49]=bag50;
newbags[50]=bag51;
newbags[51]=bag52;
newbags[52]=bag53;
newbags[53]=bag54;
newbags[54]=bag55;
newbags[55]=bag56;
newbags[56]=bag57;
newbags[57]=bag58;
newbags[58]=bag59;
newbags[59]=bag60;
newbags[60]=bag61;
newbags[61]=bag62;
newbags[62]=bag63;
newbags[63]=bag64;
newbags[64]=bag65;
newbags[65]=bag66;
newbags[66]=bag67;
newbags[67]=bag68;
newbags[68]=bag69;
newbags[69]=bag70;
newbags[70]=bag71;
newbags[71]=bag72;
newbags[72]=bag73;
newbags[73]=bag74;
newbags[74]=bag75;
newbags[75]=bag76;
newbags[76]=bag77;
newbags[77]=bag78;
newbags[78]=bag79;
newbags[79]=bag80;
newbags[80]=bag81;
newbags[81]=bag82;
newbags[82]=bag83;
newbags[83]=bag84;
newbags[84]=bag85;
newbags[85]=bag86;
newbags[86]=bag87;
newbags[87]=bag88;
newbags[88]=bag89;
newbags[89]=bag90;
newbags[90]=bag91;
newbags[91]=bag92;
newbags[92]=bag93;
newbags[93]=bag94;
newbags[94]=bag95;
newbags[95]=bag96;
newbags[96]=bag97;
newbags[97]=bag98;
newbags[98]=bag99;
newbags[99]=bag100;
return(newbags);

}





}
