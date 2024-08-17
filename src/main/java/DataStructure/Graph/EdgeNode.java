package DataStructure.Graph;

public class EdgeNode<VertexNode> {

	VertexNode vertex; // point to the vertex position of vertex array;
	int weight; // for "Net" , if it's not net graph just ignore it !
//	EdgeNode next; // 直接用java 提供的list 不自己實作指向
	
	public EdgeNode(VertexNode v, int w) {
		vertex = v;
		weight = w;
	}

	@Override
    public String toString() {
        return "Edge [vertex=" + vertex + ", weight=" + weight + "]";
    }
}
