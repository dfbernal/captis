//
//public class asdf
//{
//	import java.util.HashMap;
//
//	import java.util.Set;
//	import java.util.Map;
//
//	import java.io.Serializable;
//
//	import SimpleOpenNI.SimpleOpenNI;
//
//	import processing.core.*;
//
//	public class Body extends HashMap<JIDX, PVector> implements Serializable, Cloneable
//	{
//	/**
//	* Serial Version UID - DO NOT CHANGE PLEASE!!!
//	*/
//	private static final long serialVersionUID = 2610605569934643630L;
//	private float timestamp;
//
//	public void set(JIDX iJidx, PVector iPvector) {
//	put(iJidx, iPvector);
//	}
//	public void initialize(SimpleOpenNI context, int userId, float timestamp) {
//	this.timestamp = timestamp;
//	PVector jointPos = new PVector();
//	// note: jointPos.get() is copying each PVector as they are call-by-reference
//
//	//Get Skeleton Coordinates
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_HEAD,jointPos);
//	set(JIDX.HEAD,jointPos.get());
//	//PApplet.print(joints.get(JIDX.HEAD)+"\n");
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_NECK,jointPos);
//	set(JIDX.NECK,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_COLLAR,jointPos); //Never used this before
//	set(JIDX.LEFT_COLLAR,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_SHOULDER,jointPos);
//	set(JIDX.LEFT_SHOULDER,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_ELBOW,jointPos);
//	set(JIDX.LEFT_ELBOW,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_HAND,jointPos);
//	set(JIDX.LEFT_HAND,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_FINGERTIP,jointPos);  // always (0.0, 0.0, 0.0)
//	set(JIDX.LEFT_FINGERTIP,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_COLLAR,jointPos); //Never used this before
//	set(JIDX.RIGHT_COLLAR,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_SHOULDER,jointPos);
//	set(JIDX.RIGHT_SHOULDER,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_ELBOW,jointPos);
//	set(JIDX.RIGHT_ELBOW,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_HAND,jointPos);
//	set(JIDX.RIGHT_HAND,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_FINGERTIP,jointPos);  // always (0.0, 0.0, 0.0)
//	set(JIDX.RIGHT_FINGERTIP,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_TORSO,jointPos);
//	set(JIDX.TORSO,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_WAIST,jointPos); // always (0.0, 0.0, 0.0)
//	set(JIDX.WAIST,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_HIP,jointPos);
//	set(JIDX.LEFT_HIP,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_KNEE,jointPos);
//	set(JIDX.LEFT_KNEE,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_ANKLE,jointPos); // always (0.0, 0.0, 0.0)
//	set(JIDX.LEFT_ANKLE,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_LEFT_FOOT,jointPos);
//	set(JIDX.LEFT_FOOT,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_HIP,jointPos);
//	set(JIDX.RIGHT_HIP,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_KNEE,jointPos);
//	set(JIDX.RIGHT_KNEE,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_ANKLE,jointPos); // always (0.0, 0.0, 0.0)
//	set(JIDX.RIGHT_ANKLE,jointPos.get());
//	context.getJointPositionSkeleton(userId,SimpleOpenNI.SKEL_RIGHT_FOOT,jointPos);
//	set(JIDX.RIGHT_FOOT,jointPos.get());
//	}
//	public PVector orientation()
//	{
//	return PVecUtilities.normTo3Pt(get(JIDX.TORSO), get(JIDX.LEFT_SHOULDER), get(JIDX.RIGHT_SHOULDER));
//	}
//	@Override
//	public Object clone()
//	{
//	Body cln = new Body();
//	for (JIDX jidx : keySet()) {
//	cln.set(jidx, get(jidx));
//	}
//	return cln;
//	}
//	// interpolates a position of the body from the positions and their coefficients given in parameter
//	public static Body interpolate(Body body1, float coefficient1, Body body2, float coefficient2) {
//	// position of the body to be returned
//	Body body = new Body();
//	// to store the part of the body which position is being computed
//	JIDX iJidx;
//	// browses all parts of the body
//	for (Map.Entry<JIDX, PVector> joints_iterator : body1.entrySet()) {
//	// part of the body to interpolate
//	iJidx = joints_iterator.getKey();
//	// interpolates the position of this part of the body and stores it
//	body.set(iJidx,
//	PVector.add(PVector.mult(joints_iterator.getValue(), coefficient1), 
//	PVector.mult(body2.get(iJidx), coefficient2)));
//	}
//	// the interpolated position of the body is returned
//	return body;
//	}
//
//	public float getTimestamp() {
//	return timestamp;
//	}
//
//	public void setTimestamp(float timestamp) {
//	this.timestamp = timestamp;
//	}
//	}
//
//
//	ArrayList<Body> jointHist = new ArrayList<Body>();
//}
