package leap;

import java.io.IOException;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;


// class to create action from leap motion and 
// connect leap device to an application
class LeapListener extends Listener {
	
	public void onInit (Controller controller) {
		System.out.println ("Initialized");
	}
	
	
	public void onConnect (Controller controller) {
		System.out.println ("Connected to motion sensor");
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
	}
	
	
	public void onDisconnect (Controller controller) {
		System.out.println ("Motion sensor disconnected");
	}
	
	
	public void onExit (Controller controller) {
		System.out.println ("Exited");
	}
	
	// camera takes 300 frames per seconds
	public void onFrame (Controller controller)
	{
		// get frame controller
		Frame frame = controller.frame();
		/* System.out.println ("Frame id: " + frame.id ()	
							+ " , Timestamp: " + frame.timestamp()
							+ " , Hands: " + frame.hands().count()
							+ " Fingers : " + frame.fingers().count()
							+ " Tools" + frame.tools().count()
							+ " Gestures: " + frame.gestures().count());
		*/
		
	/*	for (Hand hand : frame.hands()) {
			String handType = hand.isLeft() ? "Left hand" : "Right hand";
			System.out.println (handType + " " +  " , id " + hand.id()
									+ ", Palm Position: " + hand.palmPosition());
		
			Vector normal = hand.palmNormal();
			Vector direction = hand.direction();
			
			
			System.out.println ("Pitch: " + Math.toDegrees(direction.pitch())
								 + " Roll: " + Math.toDegrees(normal.roll())
								 + " Yaw: " + Math.toDegrees(direction.yaw()));
			
			
		}
		*/
		
		/*
		for (Finger finger : frame.fingers()) {
			System.out.println ("finger type: " + finger.type()
								+ " ID: " + finger.id() 
								+ " Finger's length: " + finger.length() // height width of fingers are in float type and given in millimeters
								+ " Finger's width: " + finger.width());
			
			for (Bone.Type boneType : Bone.Type.values ()) {
				Bone bone = finger.bone (boneType);
				System.out.println ("Bone type: " + bone.type()
									+ " Start: " + bone.prevJoint()
									+ " End: " + bone.nextJoint()
									+ " Direction: " + bone.direction()); // direction is a vector of finger
			}
		}
		*/
		
		
		/*for (Tool tool : frame.tools()) {
			System.out.println ("Tool ID:" + + tool.id()
					   			+ "Tip position: " + tool.tipPosition()
					   			+ " Direction: " + tool.direction()
					   			+ " Width: " + tool.width()
					   			+ "Touch distance: " + tool.touchDistance());
		}
		
		*/
		
		
		GestureList gestures = frame.gestures();
		for (int i = 0; i < gestures.count(); i++)
		{
			Gesture gesture = gestures.get(i);
			
			switch (gesture.type()) {
				case TYPE_CIRCLE:
					CircleGesture circle = new CircleGesture(gesture);
					String clockwiseness;
					
					if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI / 4)
					{
						clockwiseness = "clockwise";
					}
					else
					{
						clockwiseness = "counter clockwise";
					}
			
			}
		}
		
		
	}
}

public class leapController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LeapListener listener = new LeapListener ();
		Controller controller = new Controller ();
		
		controller.addListener(listener);
		
		System.out.println ("Press enter to exit");
		
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace ();
		};
		
		controller.removeListener (listener);
	}

}
