/**
*The following class demo's how to accept command line arguments. Command line arguments are the paramters that are passed while 
*the class file is executed.Eg: java CommandLineInput 1 3 5 hello -- > Each space separated element is an argument.
**/
public class CommandLineInput{
    //The args array has all the parameters passed by commandline.
    public static void main(String args[]){
        if(args.length == 0){
            System.out.println("No Commandline arguments passed.")   
        }else{
            //Iterates over the array and prints each argument on the commandline.
            for(int i=0;i<args.length;i++){
                System.out.println("parameter"+i+"="+args[i]);
            }
        }
    }
}